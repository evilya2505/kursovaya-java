import React from 'react';
import Table from './Table';
import SearchSercion from './SearchSection';
import api from '../utils/api';

function MaxTrans(props) {
    const[data, setData] = React.useState([]);

    React.useEffect(() => {
    api.getMaxGrouppedByInfo()
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
        <SearchSercion text={["Поиск суммы наибольшей транзакции по ID клиента.", "Введите ID клиента.", "Макимальное значение:"]}/>
        <Table 
        headers={["customer_id", "tr_datetime", "mcc_code", "tr_type", "amount", "term_id"]}
        data={data} />
    </div>
  )
}

export default MaxTrans;