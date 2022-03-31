<template xmlns:v-slot="http://www.w3.org/1999/html">
  <v-parallax
      dark
      src="https://picsum.photos/1920/1080?random"
  >
    <v-card
        max-width="448"
        class="mx-auto"
        color="grey-lighten-3"
    >
      <v-layout>
        <v-app-bar
            color="teal-darken-4"
        >
          <template v-slot:image>
            <v-img
                gradient="to top right, rgba(19,84,122,.8), rgba(128,208,199,.8)"
            ></v-img>
          </template>

          <v-app-bar-title>Spring Boot + Vue 3</v-app-bar-title>

          <v-btn
              variant="outlined"
              elevation="2"
              onclick="console.log(123)"
          >
            <v-icon>mdi-plus</v-icon>
          </v-btn>

        </v-app-bar>

        <v-main>
          <v-container fluid>
            <v-row dense>
              <v-col
                  v-for="n in 4"
                  :key="n"
                  cols="12"
              >
                <v-hover v-slot="{ isHovering, props }">
                  <v-card
                      :elevation="isHovering ? 16 : 2"
                      :class="{ 'on-hover': isHovering }"
                      :to="{ path: '/'}"
                      v-bind="props"
                  >
                    <v-card-title
                        v-text="`제목 ${n}`"
                    >
                    </v-card-title>
                    <v-card-subtitle
                        v-text="`글쓴이 ${n}`"
                    >
                    </v-card-subtitle>
                    <v-card-text>
                      Lorem ipsum dolor sit amet consectetur, adipisicing elit.?
                    </v-card-text>
                    <v-card-actions>
                      <v-btn
                          variant="outlined"
                          rounded
                          text
                      >
                        <v-icon>mdi-pencil</v-icon>
                      </v-btn>
                      <v-btn
                          variant="outlined"
                          rounded
                          text
                      >
                        <v-icon>mdi-delete</v-icon>
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-hover>
              </v-col>
            </v-row>
          </v-container>
        </v-main>
      </v-layout>
    </v-card>
  </v-parallax>
</template>