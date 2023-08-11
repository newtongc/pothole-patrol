<template>
  <div class="container">
    <div class="title">Report A Pothole</div>
    <form>
      <div class="form-group" id="top">
        <label for="address">Address:</label>
        <input
          type="text"
          id="address"
          name="address"
          v-model="pothole.address"
          required
        />
      </div>
      <div class="form-group" id="top">
        <label for="zipcode">Zip Code:</label>
        <input
          type="text"
          id="zipcode"
          name="zipcode"
          v-model="pothole.zipcode"
          required
        />
      </div>
      <div class="form-group">
        <label for="otherLocDetails">Other Location Details:</label>
        <br />
        <textarea
          type="text"
          id="otherLocDetails"
          name="otherLocDetails"
          class="full-width-group"
          v-model="pothole.location_details"
          placeholder="ex near intersection of... across the street from..."
          rows="4"
        />
      </div>
      <div class="form-group">
        <label for="additionalInfo">Additional Information:</label>
        <br />
        <textarea
          type="text"
          id="additionalInfo"
          name="additionalInfo"
          class="full-width-group"
          v-model="pothole.description"
          rows="4"
          placeholder="Enter any additional information here. Enter N/A if there is none"
        />
      </div>
      <div class="checkbox-group">
        <label for="inTraffic">In Traffic?</label>
        <input
          type="checkbox"
          id="inTraffic"
          name="inTraffic"
          v-model="pothole.in_traffic"
        />
        <label for="contact">Can we contact you for more information?</label>
        <input
          type="checkbox"
          id="contact"
          name="contact"
          v-model="pothole.can_contact"
        />
        <label for="potentialForDamage">Potential For Damage?</label>
        <input
          type="checkbox"
          id="potentialForDamage"
          name="potentialForDamage"
          v-model="pothole.potential_damage"
        />
      </div>
      <input
        type="submit"
        value="Submit"
        class="submit-button"
        v-on:click.prevent="savePothole()"
      />
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
        address: "",
        zipcode: "",
        in_traffic: false,
        potential_damage: false,
        description: "",
        location_details: "",
        can_contact: false,
      },
    };
  },
  methods: {
    savePothole() {
      if (this.pothole.address.length === 0) {
        alert("Please enter an Address.");
      }
      if (this.pothole.description.length === 0) {
        alert(
          "Please fill out the Additional Information box. Enter N/A if there are none"
        );
      }
      if (this.pothole.zipcode.length != 5) {
        alert("Zip Code must contain exactly 5 digits.");
      } else if (this.pothole.location_details.length === 0) {
        alert("Please fill out Other Location Details box. ");
      } else if (
        potholeService.createPothole(this.pothole).then((response) => {
          if (response.status === 201) {
            this.$router.push("/");
          }
        })
      );
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
.title {
  text-align: center;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  color: white;
  font-size: 30px;
  font-weight: bold;
}
.container {
  background-color: rgba(128, 128, 128, 0.822);
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  width: 300px;
  margin: 10px auto;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  color: white;
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
.form-group textarea[class="full-width-group"] {
  width: 100%;
  height: 60px;
}
.form-group input[type="text"] {
  align-items: right;
}
#top {
  justify-content: space-between;
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
.checkbox-group {
  margin-top: 10px;
  display: grid;
  grid-template-columns: 1fr 30px;
  flex-direction: column;
  align-items: flex-end;
  justify-content: right;
  margin-bottom: 20px;
  margin-top: 10px;
}
.checkbox-group label {
  text-align: left;
}
#top {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between; /* Add this to distribute items evenly */
  margin-bottom: 10px;
  padding-top: 10px;
}
</style>










