<template>
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <div class="post-preview" v-for="data in datas" v-bind:key="data.id">
          <a v-bind:href="data.link" target="_blank">
            <h2 class="post-title">
              {{ data.title }}
            </h2>
            <h3 class="post-subtitle">
              {{ data.description }}
            </h3>
          </a>
          <p class="post-meta">Posted by
            {{ data.date }}</p>
          <hr>
        </div>
        <infinite-loading @infinite="infiniteHandler" spinner="waveDots"></infinite-loading>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import InfiniteLoading from 'vue-infinite-loading'

export default {
  name: 'MainContent',
  data() {
    return {
      page: 0,
      size: 10,
      datas: []  
    }
  },
  components: {
    InfiniteLoading
  },
  methods: {
    infiniteHandler($state) {
      axios.get('http://localhost:9000/api/v1/content/list2', {
        params: {
          page: this.page,
          size: this.size
        }
      })
        .then(response => {
          setTimeout(() => {
            if (response.data.content.length) {
              this.datas = this.datas.concat(response.data.content)
              $state.loaded()
              this.page += 1
              if (this.datas.length / 10 == 0) {
                $state.complete()
              }
            } else {
              $state.complete()
            }
          }, 1000);
        })
    }
  },
}
</script>
