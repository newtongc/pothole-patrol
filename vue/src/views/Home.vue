<template>
  <div class="home">
    <h2>Home</h2>
    <div class="table-container">
      <div class="filter-container">
        <input
          type="text"
          class="filter-input"
          placeholder="Filter by Street Name"
          v-model="search.address"
        />
        <input
          type="text"
          class="filter-input"
          placeholder="Filter by Zip Code"
          v-model="search.zipcode"
        />
        <!-- <input
          type="date"
          class="filter-input"
          placeholder="Filter by Reported Date"
          v-model="search.reportedDate"
        /> -->

        <select class="filter-input" v-if="isAdmin" v-model="search.severity">
          <option value="" disabled selected>Select Severity</option>
          <option value="3">Low</option>
          <option value="2">Medium</option>
          <option value="1">High</option>
        </select>
        <button @click="fetchPotholes()">Filter</button>
      </div>

      <table>
        <tr>
          <th>Nearest Address</th>
          <th>Zip Code</th>
          <th>Location Details</th>
          <th>Details</th>
          <th>Potential For Damage</th>
          <th>In Traffic</th>
          <th>Reported Date</th>
          <th>Inspected Date</th>
          <th>Inspected?</th>
          <th>Repair Date</th>
          <th>Repaired?</th>
          <th v-if="isAdmin">Severity</th>
        </tr>

        <tr v-for="pothole in potholes" :key="pothole.id">
          <td>
            <router-link
              v-if="isAdmin"
              :to="{ name: 'getPothole', params: { id: pothole.id } }"
            >
              {{ pothole.address }}
            </router-link>
            <a v-else>{{ pothole.address }}</a>
          </td>
          <td>{{ pothole.zipcode }}</td>
          <td>{{ pothole.locationDetails }}</td>
          <td>{{ pothole.description }}</td>
          <td>{{ pothole.potentialDamage }}</td>
          <td>{{ pothole.inTraffic }}</td>
          <td>{{ pothole.reportedDate }}</td>
          <td>{{ pothole.inspectedDate }}</td>
          <td>{{ pothole.inspected }}</td>
          <td>{{ pothole.repairDate }}</td>
          <td>{{ pothole.repaired }}</td>
          <td v-if="isAdmin">{{ pothole.severity }}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import potholeService from "../services/PotholeService.js";

export default {
  name: "home",
  data() {
    return {
      search: {
        address: "",
        zipcode: "",
        // reportedDate: "",
        severity: null,
      },

      potholes: [],
    };
  },
  created() {
    this.fetchPotholes();
  },
  computed: {
    isAdmin() {
      return this.$store.getters.userIsAdmin;
    },
  },
  methods: {
    fetchPotholes() {
      potholeService.listPotholes(this.search).then((response) => {
        this.potholes = response.data;
      });
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
  width: 100%;
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
  background-color: rgba(128, 128, 128, 0.822);
  border-radius: 10px;
}
th,
td {
  border: 1px solid #ddd;
  padding: 10px;
  text-align: left;
}
th {
  background-color: rgb(150, 150, 150, 0.822);
  font-weight: bold;
}
td {
  vertical-align: top;
  text-align: left;
  /* background-color: rgba(128, 128, 128, 0.7); */
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


