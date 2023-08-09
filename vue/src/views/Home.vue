<template>
  <div class="home">
    <h2>Home</h2>

    <table>
      <tr>
        <th>Location</th>
        <th>Description</th>
        <th>Severity</th>
        <th>Reported Date</th>
      </tr>

      <tr v-for="pothole in potholes" v-bind:key="pothole.id">
        <td>
          <router-link
            v-if="isAdmin"
            v-bind:to="{ name: 'getPothole', params: { id: pothole.id } }"
          >
            {{ pothole.location }}</router-link
          >

          <a v-else> {{ pothole.location }}</a>
        </td>
        <td>{{ pothole.description }}</td>
        <td>{{ pothole.severity }}</td>
        <td>{{ pothole.reported_date }}</td>
      </tr>
    </table>
  </div>
</template>

<script>
import potholeService from "../services/PotholeService.js";
export default {
  name: "home",
  data() {
    return {
      potholes: [],
    };
  },
  created() {
    potholeService.listPotholes().then((response) => {
      this.potholes = response.data;
    });
  },
  computed: {
    isAdmin() {
      return this.$store.getters.userIsAdmin;
    },
  },
};
</script>

<style>
body {
  font-family: Arial, sans-serif;
  margin: 0px;
  padding: 50px;
  background-image: url("https://c4.wallpaperflare.com/wallpaper/929/1017/70/city-lights-bokeh-lights-blurred-blurry-wallpaper-preview.jpg");
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  z-index: 0;
  height: 88vh;
  width: 90%;
}

.container {
  background-color: #ffffff;
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  width: 80%;
  margin: auto;
}

h2 {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-size: 50px;
  font-size: 24px;
  margin-bottom: 15px;
  text-align: right;
  color: white;
  position: relative;
  top: -115px;
}
table {
  border-collapse: collapse;
  width: 100%;
  margin-top: 20px;
  background-color: rgba(128, 128, 128, 0.644);
  border-radius: 10px;
}
th,
td {
  border: 1px solid #ddd;
  padding: 10px;
  text-align: left;
}
th {
  background-color: #f2f2f2;
  font-weight: bold;
}
td {
  vertical-align: top;
  text-align: left;
  background-color: rgba(128, 128, 128, 0.404);
  color: white;
}
th,
td:last-child {
  width: 20%; /* Adjust the width of the last column as needed */
}
.home {
  border-radius: 10px;
}
</style>


