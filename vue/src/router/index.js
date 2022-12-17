import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import NewLandmark from '../views/NewLandmark.vue'
import LandmarkDetails from '../views/LandmarkDetails.vue'
import Itinerary from '../views/Itinerary.vue'
import Routes from '../views/Routes.vue'
import NewReview from '../views/NewReview.vue'
import PendingLandmarks from '../views/PendingLandmarks.vue'
import AdminHome from '../views/AdminHome.vue'
import SavedItineraries from '../views/SavedItineraries.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/home',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/home/admin',
      name: 'adminHome',
      component: AdminHome,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/add",
      name: "addLandmark",
      component: NewLandmark,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/details/:id",
      name: "landmark-details",
      component: LandmarkDetails,
      meta: {
        requiresAuth: false 
      }
    },
    {
      path: "/itinerary",
      name: "itinerary",
      component: Itinerary,
      meta: {
        requiresAuth: false
      },
    },
      {
        path: "/routes",
        name: "routes",
        component: Routes,
        meta: {
        requiresAuth: false
      }
    },
    {
        // path: "/newReview/:id",
        path: "/review/new/:landmarkId",
        name: "NewReview",
        component: NewReview,
        meta: {
        requiresAuth: false
      } 
    },
    {
      path: "/pending",
      name: "pendingLandmarks",
      component: PendingLandmarks,
      meta: {
      requiresAuth: false
      }
    },
      {
        path: "/itineraries/saved",
        name: "savedItineraries",
        component: SavedItineraries,
        meta: {
        requiresAuth: false
        },
      }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
