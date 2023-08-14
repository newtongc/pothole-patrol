<template>
  <div class="inspection-form pothole-details">
    <div class="info">
      <h1 class="details">Nearest Address:</h1>
      <h1 class="potholeInfo">{{ pothole.address }}</h1>
      <h1 class="details">Description:</h1>
      <h1 class="potholeInfo">{{ pothole.description }}</h1>
      <h1 class="details">Severity:</h1>
      <h1 class="potholeInfo">{{ pothole.severity }}</h1>
      <h1 class="details">Date Reported:</h1>
      <h1 class="potholeInfo">{{ pothole.reportedDate }}</h1>

      <h1 class="details" v-if="pothole.inspectedDate != null">
        Inspected Date:
      </h1>
      <h1 class="potholeInfo" v-if="pothole.inspectedDate != null">
        {{ pothole.inspectedDate }}
      </h1>

      <h1 class="details" v-if="pothole.repairDate != null">Repair Date:</h1>
      <h1 class="potholeInfo" v-if="pothole.repairDate != null">
        {{ pothole.repairDate }}
      </h1>

      <h1 v-if="pothole.canContact" class="details">Contact Phone Number:</h1>
      <h1 v-if="pothole.canContact" class="potholeInfo">
        {{ pothole.reporter.phoneNumber }}
      </h1>
    </div>
    <form>
      <div class="checkbox-group">
        <label for="inspected">Inspected</label>
        <input type="checkbox" id="inspected" v-model="pothole.inspected" />
      </div>
      <div>
        <label for="severity">Severity:</label>
        <select id="severity" class="dropdown" v-model="pothole.severity">
          <option value="1">1 - High</option>
          <option value="2">2 - Medium</option>
          <option value="3">3 - Low</option>
        </select>
      </div>
      <div class="form-group">
        <div>
          <label for="inspectionDate">Inspection Date:</label>
          <input
            type="date"
            id="inspectionDate"
            v-model="pothole.inspectedDate"
          />
        </div>

        <div>
          <label for="repairDate">Repair Date:</label>

          <input type="date" id="repairDate" v-model="pothole.repairDate" />
        </div>
      </div>
      <div class="button-group">
        <button class="submitBtn" @click.prevent="updatePothole()">
          Submit
        </button>
        <button class="btnDelete" v-on:click.prevent="deletePothole()">
          Delete
        </button>
      </div>
    </form>
  </div>
</template>
<script>
import PotholeService from "../services/PotholeService";
export default {
  name: "pothole-details",
  data() {
    return {
      pothole: {},
      // phoneNumber: null,
    };
  },
  created() {
    const potholeId = this.$route.params.id;
    PotholeService.getPothole(potholeId)
      .then((response) => {
        this.$store.commit("SET_ACTIVE_POTHOLE", response.data);
        this.pothole = response.data;
        // this.phoneNumber = response.data.phoneNumber;
      })
      .catch((error) => {
        if (error.response.status == 404) {
          this.$router.push({ name: "NotFound" });
        }
      });
  },
  methods: {
    updatePothole() {
      alert("Submitting");
      PotholeService.updatePothole(this.pothole)
        .then((response) => {
          if (response.status == 200) {
            this.$router.push({ name: "UpdatePothole" });
            this.$router.push({ name: "home" });
          }
        })
        .catch((error) => console.error(error));
    },
    deletePothole() {
      alert("Deleting");
      PotholeService.deletePothole(this.pothole.id).then((response) => {
        if (response.status === 200) {
          this.$router.push({ name: "home" });
        }
      });
    },
  },
};
</script>
<style scoped>
.inspection-form {
  width: 50vw;
  max-width: 70vw;
  height: 50vh;
  max-height: 60vh;
  margin: 30px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: rgba(128, 128, 128, 0.822);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
.details,
.potholeInfo {
  background-color: transparent;
  font-size: 20px;
  max-width: 100%;
  text-align: left;
}
.info {
  display: grid;
  grid-template-columns: 1fr 1fr;
}

.header {
  margin-bottom: 20px;
}
label {
  font-size: 20px;
  color: white;
}
#severity {
  margin-left: 10px;
  margin-bottom: 5px;
  width: 30px;
}

/* .detail {
  font-size: 18px;
  margin-bottom: 6px;
} */

.form-title {
  font-size: 24px;
  margin-bottom: 20px;
}

.form-container {
  margin-top: 20px;
}

.checkbox-group {
  display: flex;
  align-items: center;
  margin-bottom: 12px;
}

#checkbox {
  margin-right: 20px;
}

.checkbox-label {
  font-weight: bold;
}

.form-group {
  display: grid;
  grid-template-columns: 1fr 1fr;
  margin-bottom: 20px;
}

/* .input-container {
  flex: 1;
  margin-right: 12px;
}

.input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
} */

.button-group {
  display: flex;
  margin-top: 110px;
  justify-content: space-between;
  align-items: bottom;
}

/* .btn {
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
  transition: background-color 0.3s, color 0.3s;
} */

.submitBtn {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}
.submitBtn:hover {
  background-color: #0056b3;
}

.btnDelete {
  background-color: #dc3545;
  color: #fff;
  border: none;
  padding: 10px 20px;
  margin: 0 5px;
  cursor: pointer;
  border-radius: 5px;
  font-size: 16px;
}
.btnDelete:hover {
  background-color: #ac2835;
  color: #fff;
}

/* .btn:hover {
  background-color: #333;
  color: #fff;
} */
</style>