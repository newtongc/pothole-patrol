<template>
  <div id="login">
    <form @submit.prevent="login">
      <h3>Please Sign In</h3>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <input
          type="text"
          id="username"
          v-model="user.username"
          required
          autofocus
          placeholder="Username"
        />
      </div>
      <div class="form-input-group">
        <input
          type="password"
          id="password"
          v-model="user.password"
          required
          placeholder="Password"
        />
      </div>
      <button id="loginBtn" type="submit">
        Sign in <i class="fa-solid fa-right-to-bracket" aria-hidden="true"></i>
      </button>
      <p>
        <router-link :to="{ name: 'register' }"
          >Need an account? Sign up.</router-link
        >
      </p>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}

#login {
  text-align: center;
  border-radius: 20px;
  background-color: rgba(128, 128, 128, 0.822);
  width: 35%;
  height: 40vh;
  margin: auto;
}

body {
  padding: 50;
}

h3 {
  color: white;
  font-size: 40px;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  right: -100px;
}

.form-input-group {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}
#loginBtn {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
}
#loginBtn:hover {
  background-color: #0056b3;
}
</style>