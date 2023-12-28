import React from 'react'
import "./custom-pagination.scss";


const CustomPaginaton = (props) => {

 const customPage =(number)=>{
props.customPage(number);
 }

 
  return (
    <div className="CustomPaginaton">
      <button
        className="buttonAll"
        disabled={props.page <= 0}
        onClick={() => {
          customPage(0);
        }}
      >
        &#171; 
      </button>
      <button
        className="buttonOne"
        disabled={props.page <= 0}
        onClick={() => {
          customPage(props.page - 1);
        }}
      >
        &#8249;
      </button>
      <button className="buttonText">
        {props.page + 1} /{props.totalPages}
      </button>
      <button
        className="buttonOne"
        disabled={props.page + 1 >= props.totalPages}
        onClick={() => {
          customPage(props.page + 1);
        }}
      >
        &#8250;
      </button>
      <button
        className="buttonAll"
        disabled={props.page + 1 >= props.totalPages}
        onClick={() => {
          customPage(props.totalPages - 1);
        }}
      >
        &#187;
      </button>
    </div>
  );
  
};

export default CustomPaginaton;;