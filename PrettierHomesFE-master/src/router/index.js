import React from "react";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import UserLayout from "../layouts/user-layout";
import HomePage from "../pages/home-page.js";
import ContactPage from "../pages/contact-page";
import AboutPage from "../pages/about-page";
import PropertiesPage from "../pages/properties-page";
import LoginPage from "../pages/login-page";
import RegisterPage from "../pages/register-page";
import ResetPage from "../pages/resetPage";
import ForgotPasswordPage from "../pages/forgot-password-page";
import PrivateRoute from "./private-route";
import Error401Page from "../pages/errors/error-401";
import Error404Page from "../pages/errors/error-404";
import { config } from "../helpers/config";
import DashboardPage from "../pages/dashboard/dashboard-page";
import MyProfilePage from "../pages/myProfilePage.js";
import RegisterConfirm from "../pages/registerConfirm.js";
import LoginLayout from "../layouts/login-layout";
import AdminAdvertsPage from "../pages/dashboard/admin-adverts-page";
import AdminCategoriesPage from "../pages/dashboard/admin-categories-page";
import AdminCustomersPage from "../pages/dashboard/admin-customers-page";
// import TourRequestsPage from "../pages/dashboard/admin-tour-requests-page";
import AdminTourRequestsPage from "../pages/dashboard/admin-tour-requests-page";
import AdminAdvertTypesPage from "../pages/dashboard/admin-advert-types-page";
// import AdminCustomerEditLayout from "../components/dashboard/admin-customers/admin-customer-edit-layout.js";



import AddNewAdvertPage from "../pages/dashboard/add-new-advert";
import AdminReports from "../pages/dashboard/admin-reports";


import ChangePassword from "../pages/changePassword.js";

import AdminCaytegoryNew from "../components/dashboard/admin-categories/admin-category-new.js";

import CustomerAdvertsPages from "../pages/dashboard/customer-adverts-pages.js";
import PropertyDetails from "../components/properties-page/property-details.js";




// import AdminNewAdvertPage from "../pages/dashboard/admin-new-advert-page";
// import AdvertTypes from "../components/dashboard/advertTypes/advert-types.js";
// import AdvertTypeEdit from "../components/dashboard/advertTypes/advertTypeEdit.js";
// import AdvertTypeNew from "../components/dashboard/advertTypes/advertTypeNew.js";


const router = createBrowserRouter([
  {
    path: "/",
    element: <UserLayout />,
    children: [
      {
        index: true,
        element: <HomePage />,
      },
      {
        path: "Properties/*",
        element: <PropertiesPage />,
      },
      {
        path: "/property/:slug", // Utilisez le nom que vous préférez pour l'URL
        element: <PropertyDetails />,
      },
      {
        path: "/about",
        element: <AboutPage />,
      },
      {
        path: "/contact",
        element: <ContactPage />,
      },
      {
        path: "/login",
        element: <LoginPage />,
      },
      {
        path: "/register",
        element: <RegisterPage />,
      },
      {
        path: "/reset",
        element: <ResetPage />,
      },
      {
        path: "/forgot-password",
        element: <ForgotPasswordPage />,
      }, 
      {
        path: "/new-categories",
        element: <AdminCaytegoryNew />,
      },       
      {
        path: "/registerConfirm/:code",
        element: <RegisterConfirm />,
      },
      {
        path: "unauthorized",
        element: <Error401Page />,
      },
      {
        path: "*",
        element: <Error404Page />,
      },
      {
        path: "add-new-advert",
        element: (
            <AddNewAdvertPage />
          // <PrivateRoute roles={config.pageRoles.dashboard}>
          // </PrivateRoute>
        ),
      },
    ],
  },
  {
    path: "/dashboard",
    element: <LoginLayout />,
    children: [
      {
        index: true,
        element: (
          <PrivateRoute roles={config.pageRoles.dashboard}>
            <DashboardPage />
          </PrivateRoute>
        ),
      },
      {
        path: "new-categories",
        element: 
           <PrivateRoute roles={config.pageRoles.dashboard}>
             <AdminCaytegoryNew />
           </PrivateRoute> 
        
      },
      {
        path: "users",
        element: (

          <PrivateRoute roles={config.pageRoles.customer}>
            <AdminCustomersPage />
          </PrivateRoute>
        ),
      },

      
      {


        path: "categories",
        element: (
          <PrivateRoute roles={config.pageRoles.categories}>
            <AdminCategoriesPage />
          </PrivateRoute>
        ),
      
      },
      {
        path: "adverts",
        element: (
          <PrivateRoute roles={config.pageRoles.adverts}>
            <AdminAdvertsPage />
          </PrivateRoute>
        ),
      },
      
      {
        path: "my-adverts",
        element: (
          <PrivateRoute roles={config.pageRoles.adverts}>
            <CustomerAdvertsPages />
          </PrivateRoute>
        ),
      },
      {
        path: "advert-types",
        element: (
          <PrivateRoute roles={config.pageRoles.advertType}>
            <AdminAdvertTypesPage />
          </PrivateRoute>
        ),
      },
      {
        path: "add-new-advert",
        element: (
            <AddNewAdvertPage />
          // <PrivateRoute roles={config.pageRoles.dashboard}>
          // </PrivateRoute>
        ),
        },
        {
        path: "my-profile",
        element: (
          <PrivateRoute roles={config.pageRoles.dashboard}>
            <MyProfilePage />
          </PrivateRoute>
        ),
      },

      {
        path: "change-password",
        element: (
          <PrivateRoute roles={config.pageRoles.dashboard}>
            <ChangePassword />
          </PrivateRoute>

        ),
      },
      {
        path: "tour-requests",
        element: (
          <PrivateRoute roles={config.pageRoles.tourRequests}>
            <AdminTourRequestsPage />
          </PrivateRoute>
        ),
      },
      {
        path: "reports",
        element: (
          <PrivateRoute roles={config.pageRoles.reports}>
            <AdminReports />
          </PrivateRoute>
        ),
      },
    ],
  },
]);

const AppRouter = () => {
  return <RouterProvider router={router} />;
};

export default AppRouter;
