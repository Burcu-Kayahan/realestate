import React, { useState, useEffect } from 'react';
import { getLogsData } from '../../../api/logs-service';
import { Button, Col, Container, Row } from 'react-bootstrap';
import CustomPagination from '../../common/custom-pagination';
import Spacer from '../../common/spacer';
import "./styles/admin-customer-logs.scss";
const ITEMS_PER_PAGE = 3;

const AdminCustomerLogs = (props) => {
    const [data, setData] = useState([]); 
    const [totalRows, setTotalRows] = useState(0); 
    const [totalPages, setTotalPages] = useState(0); 
    const [page, setPage] = useState(0); 

    useEffect(() => {
        const fetchData = async () => {
            try {
                const resp = await getLogsData(props.id);
                setData(resp);
                setTotalRows(resp.length); 
                const calculatedTotalPages = Math.ceil(resp.length / ITEMS_PER_PAGE); 
                setTotalPages(calculatedTotalPages);
                setPage(0); 
            } catch (err) {
                console.error(err);
            }
        };

        fetchData();
    }, [props.id]);

    const onPageChange = (pageNumber) => {
        setPage(pageNumber);
    };


    const startIndex = page * ITEMS_PER_PAGE;
    const endIndex = Math.min(startIndex + ITEMS_PER_PAGE, totalRows);
    const currentData = data.slice(startIndex, endIndex);

    return (
        <Container>
            
            <Button className='logs_title'> <span>Logs</span> </Button>
            
            <Row className="AdminCustomerLogs">
                <Col sm={10}><strong>Action</strong></Col>
                <Col sm={2}><strong>Date</strong></Col>
            </Row>
            {currentData.map((item, index)  => (
                <Row key={index} className='AdminCustomerLogs-row'>
                    <Col sm={10} className='logs-col'>{item.log}</Col>
                    <Col sm={2} className='logs-col'>{item.createAt}</Col>
                </Row>
            ))}

            <div className="ms-2 pt-3">Total Element: {totalRows}</div>

            <CustomPagination
                customPage={onPageChange}
                totalPages={totalPages}
                page={page}
            />
            <Spacer height={200} />
        </Container>
    );
};

export default AdminCustomerLogs;
