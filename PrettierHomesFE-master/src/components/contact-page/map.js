import React from "react";
import { config } from "../../helpers/config";
import "./map.scss"
const Map = () => {
  return (
    <div className="map" >
      <iframe
        src={config.contact.mapEmbedURL}
        width="100%"
        height="575vh"
        allowFullScreen=""
        loading="lazy"
        referrerPolicy="no-referrer-when-downgrade"
        style={{ display: "block" }}
        title="map"
        disabled
      ></iframe>
    </div>
  );
};

export default Map;


