import React from 'react';
import Menu from './Menu';

function Main(props) {
  return (
    <main className="content page__content">
        <Menu uploadCsvReq={props.uploadCsvReq}/>
    </main>
  )
}

export default Main;