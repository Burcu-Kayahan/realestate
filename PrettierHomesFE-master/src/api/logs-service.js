import axios from "axios";
import { config } from "../helpers/config";
import { getAuthHeader } from "./auth-header";

const BASE_URL = config.api.baseUrl;

export const getLogsData = async (id) => {

    const res = await axios.get(`${BASE_URL}/logs/${id}`, {
        headers: getAuthHeader(),
    });
  const data = await res.data;
  return data;
};

