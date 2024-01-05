import React, { useState, useEffect } from "react";
import {
  getAdvertTypeList,
  deleteAdvertType,
} from "../../../api/advert-type-service";
import {
  setCurrentRecord,
  setOperation,
} from "../../../store/slices/misc-slice";
import { swalAlert, swalConfirm } from "../../../helpers/functions/swal";

import { Container, Button } from "react-bootstrap";
import "./admin-advert-type.scss";
import { AiOutlineDelete } from "react-icons/ai";
import { FiEdit2 } from "react-icons/fi";
import { LiaSearchMinusSolid } from "react-icons/lia";
import Spacer from "../../common/spacer";
import { useDispatch, useSelector } from "react-redux";
import AdminAdvertTypeEdit from "./admin-advert-type-edit";

const AdminAdvertTypes = (props) => {
  const [loading, setLoading] = useState(true);
  const [search, setSearch] = useState("");
  const [data, setData] = useState(null);
  const dispatch = useDispatch();
  const { listRefreshToken } = useSelector((state) => state.misc);

  const pushData = (row) => {
    props.getData(row);
  };

  const fetchData = async () => {
    try {
      const resp = await getAdvertTypeList();
      console.log(resp);
      setData(resp);
    } catch (err) {
      console.log(err);
    } finally {
      setLoading(false);
    }
  };


  const handleEdit = (row) => {

     dispatch(setOperation(null));
    pushData(row);
    dispatch(setCurrentRecord(row));
    
    dispatch(setOperation("edit"));
  };

  const getOperationButtons = (x) => {
    return (
      <>
        <Button className="btn-link icons" onClick={() => handleEdit(x)}>
          <AiOutlineDelete />
        
        </Button>
        <Button className="btn-link icons " onClick={() => handleEdit(x)}>
          <FiEdit2 />
        </Button>
      </>
    );
  };

  const handleNewAdvertType = () => {
    dispatch(setOperation("new"));
  };

  useEffect(() => {
    fetchData();
  }, [search, listRefreshToken]);

  return (
    <Container className="adTypePage">
      <Spacer height={20} />
      <div className=" col ">
        <div className=" inputDiv">
          <input
            placeholder="Type something"
            className=""
            onChange={(e) => {
              setSearch(e.target.value);
            }}
            type="text"
          />
          <button className="iconsSearch">
            <LiaSearchMinusSolid />
          </button>
        </div>

        <button className=" addNew " onClick={handleNewAdvertType}>
          ADD NEW
        </button>
      </div>
      <div className="m-3 p-3 d-flex justify-content-between rounded fw-bold capt ">
        <div className="w-25 text-left title">Title</div>
        <div className="w-25 text-end  action ">Action</div>
      </div>

      {data?.map((x) => {
        return (
         
          <div
         
           
            className="m-3 g-3  d-flex justify-content-between rounded"
            style={{ backgroundColor: "#F4F4F4" }}
          >
            <div className="m-3 w-25 text-left d-flex align-items-center row">
               {x.title.replace(/s$/, "")}
            </div>

            <div className="w-25 text-end me-4 ">{getOperationButtons(x)}</div>
          </div>
        );
      })}
    </Container>
  );
};

export default AdminAdvertTypes;
