import { resetPassword } from '../../api/user-service'; // Import your API service


export const isValid = (formik, field) => formik.touched[field] && !formik.errors[field]

export const isInValid = (formik, field) => formik.touched[field] && formik.errors[field]


// Assuming you're using Redux for state management

// Action Types
const RESET_PASSWORD_REQUEST = 'RESET_PASSWORD_REQUEST';
const RESET_PASSWORD_SUCCESS = 'RESET_PASSWORD_SUCCESS';
const RESET_PASSWORD_FAILURE = 'RESET_PASSWORD_FAILURE';

// Action Creators
const resetPasswordRequest = () => ({ type: RESET_PASSWORD_REQUEST });
const resetPasswordSuccess = (payload) => ({ type: RESET_PASSWORD_SUCCESS, payload });
const resetPasswordFailure = (error) => ({ type: RESET_PASSWORD_FAILURE, payload: error });

// Async Action Creator
export const resetPasswordAsync  = (values) => async (dispatch) => {
  dispatch(resetPasswordRequest());

  try {
    // Call your API function for password reset
    await resetPassword(values);

    // Dispatch success action if the reset is successful
    dispatch(resetPasswordSuccess());
  } catch (error) {
    // Dispatch failure action if an error occurs
    dispatch(resetPasswordFailure(error.message));
  }
};
