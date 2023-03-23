import React from 'react';
import SearchSercion from './SearchSection';
import Table from './Table';
import api from '../utils/api';

function AbsTrans(props) {
    const[data, setData] = React.useState([]);
    React.useEffect(() => {
    api.getAbsGrouppedByInfo()
    .then((data) => {
        console.log(data);
        setData(data);
    })
    .catch((err) => {
        console.log(err);
    });
    }
    ,[]);

  return (
    <div className="trans">
        <SearchSercion text={["Поиск наиболее часто встречающегося модуля транзакции по ID клиента.", "Введите ID клиента.", "Результат:"]}/>
        <Table 
        headers={["customer_id", "tr_datetime", "mcc_code", "tr_type", "amount", "term_id"]}
        data={data}/>
    </div>
  )
}

export default AbsTrans;