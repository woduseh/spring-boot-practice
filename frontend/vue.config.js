const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

  outputDir: "../src/main/resources/static/vue",
  indexPath: "../../templates/vue-frontend.html",

  devServer: {
    proxy: 'http://localhost:8080'
  },

  chainWebpack: config => {
    const svgRule = config.module.rule("svg")
    svgRule.uses.clear()
    svgRule.use("vue-svg-loader").loader("vue-svg-loader")
  }
})
