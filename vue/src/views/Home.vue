<template>
  <div class="home">
    <h2>Home</h2>

<<<<<<< HEAD
    <div class="filter-container">
      <input
        type="text"
        class="filter-input"
        placeholder="Filter by Street Name"
        v-model="filterStreet"
      />
      <input
        type="text"
        class="filter-input"
        placeholder="Filter by Zip Code"
        v-model="filterZipCode"
      />
      <input
        type="date"
        class="filter-input"
        placeholder="Filter by Reported Date"
        v-model="filterReportedDate"
      />
      <select class="filter-input" v-if="isAdmin" v-model="filterSeverity">
        <option value="">Filter by Severity</option>
        <option value="Low">Low</option>
        <option value="Medium">Medium</option>
        <option value="High">High</option>
      </select>
    </div>
    <div class="table-container">
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
=======
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
>>>>>>> 29c2888bf20ec2328e8a63df8c36ed7507ef917d

        <tr v-for="pothole in potholes" v-bind:key="pothole.id">
          <td>
            <router-link
              v-if="isAdmin"
              v-bind:to="{ name: 'getPothole', params: { id: pothole.id } }"
            >
              {{ pothole.address }}</router-link
            >

            <a v-else> {{ pothole.address }}</a>
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
      filterAddress: "",
      filterZipCode: "",
      filterReportedDate: "",
      filterInTraffic: false,
      filterSeverity: "",
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
    filteredPotholes() {
      return this.potholes.filter((pothole) => {
        return (
          pothole.address
            .toLowerCase()
            .includes(this.filterAddress.toLowerCase()) &&
          pothole.zipcode.includes(this.filterZipCode) &&
          (this.filterReportedDate === "" ||
            pothole.reportedDate === this.filterReportedDate) &&
          (!this.filterInTraffic || pothole.inTraffic) &&
          (this.isAdmin ? pothole.severity === this.filterSeverity : true)
        );
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


