<template xmlns:v-slot="http://www.w3.org/1999/html">
  <v-parallax
      dark
      src="https://cdn.vuetifyjs.com/images/backgrounds/vbanner.jpg"
  >
    <v-card max-width="448" class="mx-auto" color="grey-lighten-3">
      <v-layout>
        <v-app-bar
            color="teal-darken-4"
            image="https://picsum.photos/1920/1080?random"
        >
          <template v-slot:image>
            <v-img
                gradient="to top right, rgba(19,84,122,.8), rgba(128,208,199,.8)"
            ></v-img>
          </template>


          <v-app-bar-title>Title</v-app-bar-title>

          <v-spacer></v-spacer>

          <v-slot:template>
            <v-btn
                variant="outlined"
                elevation="2"
                icon
            >
              <v-icon
                  onclick=""
              >mdi-pencil
              </v-icon>
            </v-btn>
          </v-slot:template>

        </v-app-bar>

        <v-main>
          <v-container fluid>
            <v-row dense>
              <v-col
                  v-for="n in 4"
                  :key="n"
                  cols="12"
              >
                <v-card
                    :title="`Content ${n}`"
                    :subtitle="`Subtitle for Content ${n}`"
                    text="Lorem ipsum dolor sit amet consectetur, adipisicing elit.?"
                ></v-card>
              </v-col>
            </v-row>
          </v-container>
        </v-main>
      </v-layout>
    </v-card>
  </v-parallax>
</template>