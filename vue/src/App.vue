<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }"></router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"></router-link>
    </div>
    <router-view />
  </div>
</template>
