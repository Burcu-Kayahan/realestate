import React, { useEffect, useState } from "react";
import { Button, Container } from "react-bootstrap";
import "../../dashboard/admin-categories/admin-category.scss";
import Spacer from "../../common/spacer";
import CustomPaginaton from "../../common/custom-pagination";
import { AiOutlineDelete } from "react-icons/ai";
import { FiEdit2 } from "react-icons/fi";
import { LiaSearchMinusSolid } from "react-icons/lia";
import { getAllCategoryForAdmin } from "../../../api/category-service";
import { useDispatch } from "react-redux";
import { setCurrentRecord, setOperation } from "../../../store/slices/misc-slice";

const Categories = () => {
  const [loading, setLoading] = useState(true);
  const [data, setData] = useState(null);
  const [totalRows, setTotalRows] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [flag, setFlag] = useState(false);
  const [search, setSearch] = useState("");
  const [page, setPage] = useState(0);
  const dispatch = useDispatch();
  const [lazyState, setlazyState] = useState({
    first: 0,
    rows: 3,
    page: 0,
    sortField: "",
    sortOrder: "DESC",
  });

  const loadData = async (page, size, sort, type) => {
    try {
      const resp = await getAllCategoryForAdmin(page, size, sort, type, search);
      console.log(resp);
      setTotalRows(resp.totalElements);
      setTotalPages(resp.totalPages);
      setData(resp.content);
      setPage(resp.number)
    } catch (err) {
      console.log(err);
    } finally {
      setLoading(false);
    }
  };

  const onPage = (event) => {
    setlazyState((prevState) => {
      return {
        ...prevState,
        first: event.first,
        page: event.first / event.rows,
        rows: event.rows,
      };
    });
  };

  useEffect(() => {
    loadData(
      page,
      lazyState.rows,
      lazyState.sortField,
      lazyState.sortOrder
    );
  }, [lazyState, search, flag, page]);

  const customPage =(page)=>{
      setPage(page)
  }

  
  const getOperationButtons = (x) => {
    return (
      <>
        <Button className="btn-link icons" onClick={() => "handleDelete" }>
          <AiOutlineDelete />
        </Button>
        <Button className="btn-link icons " onClick={() =>{
        
          dispatch(setOperation("edit"))
          dispatch(setCurrentRecord(x))} }>
          <FiEdit2 />
        </Button>
      </>
    );
  };
 console.log(search)

  return (
    <Container className="categoryList">
      <Spacer height={20} />
      <div className="inputDiv">
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
      

      <div className="m-3  g-3 p-3 d-flex justify-content-between rounded fw-bold">
        <div className="w-25 text-left ">Icon</div>
        <div className="w-25 text-left">Name</div>
        <div className="w-25 text-left">Sequence</div>
        <div className="w-25 text-left">Active</div>
        <div className="w-25 text-end me-4">Action</div>
      </div>
      {data?.map((x) => {
        return (
          <>
            <div
              className="m-3  g-3  d-flex justify-content-between rounded"
              style={{ backgroundColor: "#F4F4F4" }}
            >
              <div className="ms-3 w-25 text-left d-flex align-items-center">
              Icon
              </div>
              <div className="w-25 text-left d-flex align-items-center">
             {x.title}
              </div>
              <div className="w-25 text-left d-flex align-items-center phoneColumn ">
                15
              </div>
              <div className="w-25 text-left d-flex align-items-center phoneColumn ">
                Active
              </div>
              <div className="w-25 text-end me-4">{getOperationButtons(x)}</div>
            </div>
          </>
        );
      })}
      <p className="ms-4">Total Element : {totalRows}</p>
      <CustomPaginaton
        customPage={customPage}
        totalPages={totalPages}
        page={page}
      />
      
    </Container>

  );
};

export default Categories;
