<template>
  <div class="container">
    <h1>Report A Pothole</h1>
    <form>
      <label for="location">Location:</label>
      <input
        type="text"
        id="location"
        name="location"
        v-model="pothole.location"
        required
      /><br /><br />
      <label for="description">Description:</label>
      <input
        type="text"
        id="description"
        name="description"
        v-model="pothole.description"
        required
      /><br /><br />
      <label for="severity">Severity:</label>
      <select id="severity" name="severity" v-model="pothole.severity">
        <option value="1">1 - Low</option>
        <option value="2">2 - Medium</option>
        <option value="3">3 - High</option></select
      ><br /><br />
      <label for="contact">Can we contact you for for more information? </label>
      <input type="checkbox" id="contact" name="contact" /><br /><br />
      <input type="submit" value="Submit" v-on:click.prevent="savePothole()" />
    </form>
  </div>
</template>

<script>
import potholeService from "../services/PotholeService.js";

export default {
  name: "pothole-form",
  data() {
    return {
      pothole: {
        description: "",
        location: "",
        severity: null,
      },
    };
  },
  methods: {
    savePothole() {
      potholeService.createPothole(this.pothole).then((response) => {
        if (response.status === 201) {
          this.$router.push("/");
        }
      });
    },
  },
};
</script>

<style scoped>
body {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin: 0;
  background-color: #f5f5f5;
}
.container {
  background-color: rgba(128, 128, 128, 0.671);
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  width: 300px;
  margin: auto;
}
form {
  display: flex;
  flex-direction: column;
  /* text-align: center;
  border-radius: 20px;
  background-color: gray;
  width: 50%;
  height: 40vh; */
  margin: auto;
}
label,
input,
select,
checkbox {
  margin-bottom: 10px;
  font-weight: bold;
}
input[type="submit"] {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
}
input[type="submit"]:hover {
  background-color: #0056b3;
}
.container > h1 {
  font-size: 30px;
}
</style>