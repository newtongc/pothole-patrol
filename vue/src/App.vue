<template>
  <div id="app">
    <div id="nav">
      <button id="homebtn" @click="$router.push({ name: 'home' })">Home</button>
      <button id="homebtn" @click="$router.push({ name: 'map' })">Map</button>
      <!-- &nbsp;|&nbsp; -->
      <button
        id="homebtn"
        v-if="$store.state.token == ''"
        @click="$router.push({ name: 'login' })"
      >
        Login
      </button>
      <!-- &nbsp;|&nbsp; -->
      <button id="homebtn" @click="$router.push({ name: 'addPothole' })">
        Report A Pothole
      </button>
      <!-- &nbsp;|&nbsp; -->
      <button
        id="homebtn"
        v-if="$store.state.token != ''"
        @click="$router.push({ name: 'logout' })"
      >
        Logout
      </button>

      <router-view />
    </div>
  </div>
</template>




<style>
/* @import url("https://fonts.googleapis.com/css2?family=Cherry+Bomb+One&display=swap"); */
#nav {
  padding: 10px;
  text-align: left;
}

#homebtn {
  background-color: rgba(85, 85, 85, 0.822);
  color: #fff;
  border: none;
  padding: 10px 20px;
  margin: 0 5px;
  margin-bottom: 10px;
  cursor: pointer;
  border-radius: 5px;
  font-size: 16px;
}

#homebtn:hover {
  background-color: #777;
}
</style>

