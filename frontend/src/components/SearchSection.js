import React from 'react';
import { createBrowserHistory } from "history";
import api from '../utils/api';

function SearchSercion({ text }) {
  const[result, setResult] = React.useState("");
  const[textInput, setTextInput] = React.useState("");
  const history = createBrowserHistory();

  function textInputHandler(e) {
    setTextInput(e.target.value);
  }

  function handleSubmit(e) {
    e.preventDefault();
    switch(history.location.pathname.includes('/maxtrans')) {
        case true:
            api.getMaxTransactionById(textInput)
            .then((data) => {
                setResult(data);
              console.log(data);
            })
            .catch((err) => {
                setResult("");
                console.log(err);
            });
            break;
        case false:
            api.getAbsTransactionById(textInput)
            .then((data) => {
                setResult(data);
              console.log(data);
            })
            .catch((err) => {
                setResult("");
                console.log(err);
            });
            break;
        default:
            break;
    }
    console.log(textInput);
    console.log(history.location.pathname);
  }

  return (
    <form onSubmit={handleSubmit}>
    <fieldset className="search-section">
        <p className="content__text">{text[0]}</p>
        <label className='search-section__label'>
        {text[1]}
        <input onChange={textInputHandler} type="text" name="name" className='search-section__input'/> 
        </label>
        <button type="submit" className="search-section__btn">Получить значение</button>
        <p className='content__text'>{`${text[2]} ${result}`}</p>
    </fieldset>
    </form>
  )
}

export default SearchSercion;