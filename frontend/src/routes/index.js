import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from '@/views/LoginPage.vue'
import SelectLoginPage from '@/views/SelectLoginPage.vue'
import MainPage from '@/views/MainPage.vue'
import SurveySetPage from '@/views/SurveySetPage.vue'
import SurveyExpected from '@/views/SurveyExpected.vue'
import SurveyProceeding from '@/views/SurveyProceeding.vue'
import SurveyCompleted from '@/views/SurveyCompleted.vue'
import MySurveyExpected from '@/views/MySurveyExpected.vue'
import MySurveyProceeding from '@/views/MySurveyProceeding.vue'
import MySurveyCompleted from '@/views/MySurveyCompleted.vue'
Vue.use(VueRouter)

export default new VueRouter({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: LoginPage,
    },
    {
      path: '/select',
      name: 'SelectLoginPage',
      component: SelectLoginPage,
    },
    {
      path: '/main',
      name: 'MainPage',
      component: MainPage,
    },
    {
      path: '/surveyset',
      name: 'SurveySetPage',
      component: SurveySetPage,
    },
    {
      path: '/survey/state/expected',
      name: 'SurveyExpected',
      component: SurveyExpected,
    },
    {
      path: '/survey/state/proceeding',
      name: 'SurveyProceeding',
      component: SurveyProceeding,
    },
    {
      path: '/survey/state/completed',
      name: 'SurveyCompleted',
      component: SurveyCompleted,
    },
    {
      path: '/my-survey/state/expected',
      name: 'MySurveyExpected',
      component: MySurveyExpected,
    },
    {
      path: '/my-survey/state/proceeding',
      name: 'MySurveyProceeding',
      component: MySurveyProceeding,
    },
    {
      path: '/my-survey/state/completed',
      name: 'MySurveyCompleted',
      component: MySurveyCompleted,
    },
  ],
})
