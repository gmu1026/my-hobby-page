<template>
  <div id="app">
    <navigation></navigation>
    <page-header></page-header>
    <main-content></main-content>
  </div>
</template>

<script>
import Navigation from './components/Navigation.vue'
import PageHeader from './components/PageHeader.vue'
import MainContent from './components/MainContent.vue'
import axios from 'axios'

export default {
  name: 'App',
  data() {
    return {
      datas : [],
      page : 0,
      size : 20
    }
  },
  components: {
    Navigation,
    PageHeader,
    MainContent
  },
  created() {
    axios.get('http://localhost:9000/api/v1/content/list2', {
      params: {
        page: this.page,
        size: this.size
      }
    }).then(response => {
      this.datas = response.data.content
    })
  },
}
</script>

<style>
@import './assets/style/clean-blog.min.css';

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
