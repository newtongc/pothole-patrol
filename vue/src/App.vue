<template>
  <div id="app">
    <div id="nav">
      <button @click="$router.push({ name: 'home' })">Home</button>&nbsp;|&nbsp;
      <button @click="$router.push({ name: 'login' })">Login</button
      >&nbsp;|&nbsp;
      <button @click="$router.push({ name: 'addPothole' })">
        Report A Pothole</button
      >&nbsp;|&nbsp;
      <button
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
#nav {
  padding: 10px;
  text-align: left;
}

#nav button {
  background-color: rgba(85, 85, 85, 0.822);
  color: #fff;
  border: none;
  padding: 10px 20px;
  margin: 0 5px;
  cursor: pointer;
  border-radius: 5px;
  font-size: 16px;
}

#nav button:hover {
  background-color: #777;
}
</style>

