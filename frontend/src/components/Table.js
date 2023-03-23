import React from 'react';
import sort from '../images/sort.png';

function Table({ name, headers, data, onSort }) {
    const [reversed, setReversed] = React.useState(false);
    function handleReverseButton(e) {
        e.preventDefault();
        console.log(data.reverse());
        setReversed(!reversed);
    }
  return (
    <div className="table-section">
    <button className="table-section__btn" onClick={handleReverseButton}><img src={sort} className="table-section__btn-img"/></button>
    <h2 className='table-section__name'>{name}</h2>
    <table className='table'>
    <tr className='table__tr'>
        {Array.isArray(headers) && headers.map(header => {
    return (<th className="table__th">{header}</th>)
        })}</tr>
        {reversed ? Array.isArray(data.reverse()) && data.reverse().map(inf => {
    return (<tr>
        <td className="table__td">{inf.customer_id}</td>
        <td className="table__td">{inf.datetime}</td>
        <td className="table__td">{inf.code_id}</td>
        <td className="table__td">{inf.type_id}</td>
        <td className="table__td">{inf.amount}</td>
        <td className="table__td">{inf.term_id}</td>
    </tr>)
        }) : Array.isArray(data) && data.map(inf => {
            return (<tr>
                <td className="table__td">{inf.customer_id}</td>
                <td className="table__td">{inf.datetime}</td>
                <td className="table__td">{inf.code_id}</td>
                <td className="table__td">{inf.type_id}</td>
                <td className="table__td">{inf.amount}</td>
                <td className="table__td">{inf.term_id}</td>
            </tr>)
                })}
        {}
    </table>
    </div>
  )
}

export default Table;