import Main from "./Main";
import React from 'react';
import api from '../utils/api';
import { Route } from 'react-router-dom';
import { Routes } from 'react-router-dom';
import AbsTrans from "./AbsTrans";
import MaxTrans from "./MaxTrans";

function App() {
  function uploadCsvReq(file, type) {
    api.postCsv(file, type)
    .then((data) => {
      console.log(data);
    })
    .catch((err) => {
        console.log(err);
    });
  }

  return (
    <div className="page">
      <div className="content">
      <Routes>
          <Route path="/maxtrans" element={<MaxTrans />}/>
          <Route path="/abstrans" element={<AbsTrans />}/>
          <Route path="/" element={<Main uploadCsvReq={uploadCsvReq}/>}/>
      </Routes>
      </div>
    </div>
  );
}

export default App;
