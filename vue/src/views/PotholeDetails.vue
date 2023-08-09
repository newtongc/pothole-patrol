<template>
  <div class="inspection-form pothole-details">
    <h1>Location: {{ pothole.location }}</h1>
    <h1>Description: {{ pothole.description }}</h1>
    <h1>Severity: {{ pothole.severity }}</h1>
    <h2>Inspection Form</h2>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="inspectionDate">Inspection Date:</label>
        <input type="date" id="inspectionDate" />
      </div>
      <div>
        <label for="inspected">Inspected</label>
        <input type="checkbox" id="inspected" />
      </div>
      <div>
        <label for="repairDate">Repair Date:</label>
        <input type="date" id="repairDate" />
      </div>
      <div>
        <label for="reported-Date">Reported Date:</label>
        <input type="date" id="reportedDate" />
      </div>
      <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
import PotholeService from "../services/PotholeService";

export default {
  name: "pothole-details",
  //   props: { potholeID: Number },
  data() {
    return {
      pothole: {},
      //   id: null,
      //   location: "",
      //   description: "",
      //   severity: null,
    };
  },
  created() {
    const potholeId = this.$route.params.id;
    PotholeService.getPothole(potholeId)
      .then((response) => {
        this.$store.commit("SET_ACTIVE_POTHOLE", response.data);
        this.pothole = response.data;
      })
      .catch((error) => {
        if (error.response.status == 404) {
          this.$router.push({ name: "NotFound" });
        }
      });
  },
};
</script>

<style scoped>
h2 {
  top: -230px;
}
h1 {
  font-size: 15px;
}
.inspection-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 70vh;
  background-color: rgba(128, 128, 128, 0.616);
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  width: 300px;
  margin: auto;
}
.form-container {
  width: 100%;
  max-width: 400px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: #fff;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.form-group {
  margin-bottom: 1px;
}
label {
  display: flex;
  font-weight: bold;
  margin: auto;
  text-align: center;
  justify-content: center;
}
input[type="text"],
textarea,
input[type="date"] {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-bottom: 10px;
}
input[type="checkbox"] {
  display: flex;
  margin: auto;
  margin-bottom: 10px;
}
button[type="submit"] {
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  padding: 10px 20px;
  cursor: pointer;
  transition: background-color 0.3s;
}
button[type="submit"]:hover {
  background-color: #0056b3;
}
button {
  display: flex;
  margin: auto;
}

input #inspected {
  display: flex;
  justify-content: center;
}
</style>