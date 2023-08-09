<template>
  <div class="home">
    <h1>Home</h1>

    <table>
      <tr>
        <th>Location</th>
        <th>Description</th>
        <th>Severity</th>
      </tr>

      <tr v-for="pothole in potholes" v-bind:key="pothole.id">
        <td>
          <router-link
            v-bind:to="{ name: 'getPothole', params: { id: pothole.id } }"
          >
            {{ pothole.location }}</router-link
          >
        </td>
        <td>{{ pothole.description }}</td>
        <td>{{ pothole.severity }}</td>
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
};
</script>

<style>
body {
  font-family: Arial, sans-serif;
  margin: 0px;
  padding: 50px;
  background-color: #696969;
}
.container {
  background-color: #ffffff;
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  width: 80%;
  margin: auto;
}

h1 {
  font-size: 24px;
  margin-bottom: 15px;
}
table {
  border-collapse: collapse;
  width: 100%;
  margin-top: 20px;
  background-color: white;
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
}
th,
td:last-child {
  width: 20%; /* Adjust the width of the last column as needed */
}
</style>


