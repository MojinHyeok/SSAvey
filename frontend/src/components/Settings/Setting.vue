<template>
  <v-app>
    <v-card class="mx-auto mt-5" color="#3396f4" dark width="600">
      <v-card-title>
        <v-list-item class="grow">
          <v-list-item-avatar color="grey darken-3">
            <v-img
              class="elevation-6"
              alt=""
              src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
            ></v-img>
          </v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-title>
              <h2>{{ user.name }} 님</h2>
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-card-title>
      <hr />
      <v-card-text class="text-h5 font-weight-bold">
        <div class="myinfo-contents">
          <div class="userinfo">
            <div class="content">
              <h4>이메일</h4>
              <input v-model="user.email" :disabled="true" />
            </div>
            <div class="content">
              <h4>직책</h4>
              <input v-model="user.position" :disabled="is_modify" />
            </div>
            <div class="content">
              <h4>기수</h4>
              <input v-model="user.generation" :disabled="is_modify" />
            </div>
            <div class="content">
              <h4>지역</h4>
              <input v-model="user.area" :disabled="is_modify" />
            </div>
            <div class="content">
              <h4>반</h4>
              <input v-model="user.group" :disabled="is_modify" />
            </div>
            <div class="content">
              <h4>팀</h4>
              <input v-model="user.team" :disabled="is_modify" />
            </div>
            <div class="content">
              <h4>팀 내 역할</h4>
              <input v-model="user.team_roll" :disabled="is_modify" />
            </div>
          </div>
        </div>
      </v-card-text>
      <v-card-actions>
        <v-row align="center" justify="space-between">
          <v-btn
            v-if="is_modify"
            @click="Update"
            light
            class="mx-5 my-5"
            color="white"
          >
            <i class="fas fa-edit fa-sm"></i>수정
          </v-btn>
          <v-btn
            v-if="!is_modify"
            @click="modify"
            light
            class="mx-5 my-5"
            color="white"
            ><i class="fas fa-pen fa-sm"></i>완료</v-btn
          >
          <v-btn
            v-if="!is_modify"
            @click="back"
            light
            class="mx-5 my-5"
            color="warning"
            ><i class="fas fa-window-close fa-sm"></i>취소</v-btn
          >
          <v-btn light class="mx-5 my-5" color="error">
            <i class="fas fa-times-circle"></i>회원탈퇴
          </v-btn>
          <!-- <v-menu bottom :offset-x="offset">
            <template v-slot:activator="{ on, attrs }">
              <v-btn color="primary" dark v-bind="attrs" v-on="on">
                <i class="fas fa-ellipsis-v"></i>
              </v-btn>
            </template>
            <v-list>
              <v-list-item>
                <v-list-item-title
                  >결과보기
                  <i class="fas fa-file-alt fa-sm" style="float: right;"></i
                ></v-list-item-title>
              </v-list-item>
              <v-divider></v-divider>
              <v-list-item>
                <v-list-item-title
                  >공유
                  <i class="fas fa-share-alt fa-sm" style="float: right;"></i
                ></v-list-item-title>
              </v-list-item>
              <v-divider></v-divider>
              <v-list-item>
                <v-list-item-title
                  >삭제<i
                    class="fas fa-trash-alt fa-sm"
                    style="float: right;"
                  ></i
                ></v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu> -->
        </v-row>
      </v-card-actions>
    </v-card>
    <v-carousel
      cycle
      height="400"
      hide-delimiter-background
      show-arrows-on-hover
    >
      <v-carousel-item v-for="(image, i) in images" :key="i">
        <!-- <v-sheet :color="colors[i]" height="100%">
          <v-row class="fill-height" align="center" justify="center">
            <div class="text-h2">{{ slide }} Slide</div>
          </v-row>
        </v-sheet> -->
        <img
          class="mx-auto"
          :src="image.url"
          style="height: 100%; object-fit: contain;"
          :alt="image.alt"
        />
      </v-carousel-item>
    </v-carousel>
  </v-app>
</template>
<script>
import UserApi from '@/api/UserApi'

export default {
  data() {
    return {
      user: {},
      is_modify: true,
      offset: true,
      dialog: false,
      images: [
        { url: require('@/assets/설문조사이미지.jpg'), alt: 'carousel1' },
        { url: require('@/assets/bigLogo.png'), alt: 'carousel1' },
      ],
    }
  },
  methods: {
    Update() {
      this.is_modify = false
    },
    back() {
      this.is_modify = !this.is_modify
    },
    modify() {
      UserApi.updateUser(
        {
          uid: this.user.uid,
          email: this.user.email,
          name: this.user.name,
          position: this.user.position,
          generation: this.user.generation,
          area: this.user.area,
          group: this.user.group,
          team: this.user.team,
          team_roll: this.user.team_roll,
          template: this.user.template,
          mySurvey: this.user.mySurvey,
          survey: this.user.survey,
        },
        res => {
          alert(res.data.message)
          this.$store.commit('setUser', this.user)
          this.is_modify = !this.is_modify
        },
      )
    },
  },
  computed: {
    isValid() {
      return (
        this.user.position &&
        this.user.generation &&
        this.user.area &&
        this.user.team &&
        this.user.team_roll
      )
    },
  },
  created() {
    this.user = this.$store.state.user
  },
}
</script>

<style scoped>
.content {
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>
