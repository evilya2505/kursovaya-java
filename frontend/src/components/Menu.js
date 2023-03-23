import React from 'react';
import { NavLink } from 'react-router-dom';
import FormSection from './FormSection';

// Обработчик кнопки сабмит

function Menu(props) {
  return (
    <div className="menu main__menu">
        <div className='menu__content'>
            <p className='content__text'>Добавить данные в таблицу</p>
            <div className="menu__btns-bottom menu__btns-bottom_visible">
            <FormSection formName="add-client" text="Клиенты" uploadCsvReq={props.uploadCsvReq}/>

            </div>
            <p className='content__text'>Информация</p>
            <div className="menu__btns-bottom">
                <NavLink to="/maxtrans" className="menu__link">Максимальные поступления по транзакциям</NavLink>
                <NavLink to="/abstrans" className="menu__link">Модули поступления по транзакциям</NavLink>
            </div>
        </div>
    </div>
  )
}

export default Menu;