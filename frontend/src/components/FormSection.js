import React from 'react';

function FormSection(props) {
    const fileInput = React.useRef(null);
    const[file, setFile] = React.useState(null);
    const[isTransaction, setIsTransaction] = React.useState(false);
    const[isGender, setIsGender] = React.useState(false);
    const[isType, setIsType] = React.useState(false);
    const[isCostumer, setisCostumer] = React.useState(false);
    const[isCode, setIsCode] = React.useState(false);
    const[fileName, setFileName] = React.useState("Файл не выбран");

    function offAllRadios() {
        setIsTransaction(false);
        setIsGender(false);
        setIsType(false);
        setisCostumer(false);
        setIsCode(false);
    }
    function handleSubmit(e) {
        e.preventDefault();
        let type = "";

        switch(true) {
            case isTransaction:
                type = 'transaction';
                break;
            case isGender:
                type = 'gender';
                break;
            case isType:
                type = 'type';
                break;
            case isCostumer:
                type = 'customer';
                break;
            case isCode:
                type = 'code';
                break;
            default:
                break;
        }

        props.uploadCsvReq(file, type);
        console.log(type);
    }

    function handleInput(e) {
        setFile(e.target.files[0]);
        setFileName(e.target.value.split("\\")[e.target.value.split("\\").length - 1]);
    }

    function handleRadio(e) {
        offAllRadios();
        switch(e.target.value) {
            case 'transaction':
                setIsTransaction(!isTransaction);
                break;
            case 'gender':
                setIsGender(!isGender);
                break;
            case 'type':
                setIsType(!isType);
                break;
            case 'customer':
                setisCostumer(!isCostumer);
                break;
            case 'code':
                setIsCode(!isCode);
                break;
            default:
                break;
        }
    }

  return (
    <div className="form-seciton">
       <form onSubmit={handleSubmit}>
       <label className='form__section-label'>
        Выберите таблицу для загрузки данных
        <div className='form-section__radios'>
        <p className='form-section__radio'><input onClick={handleRadio} name="choosen-table" type="radio" value="transaction" checked={isTransaction}/>Транзакции</p>
        <p className='form-section__radio'><input onClick={handleRadio} name="choosen-table" type="radio" value="gender" checked={isGender} />Пол</p>
        <p className='form-section__radio'><input onClick={handleRadio} name="choosen-table" type="radio" value="type"  checked={isType}/>Типы транзакции</p>
        <p className='form-section__radio'><input onClick={handleRadio} name="choosen-table" type="radio" value="customer"  checked={isCostumer}/>Клиенты</p>
        <p className='form-section__radio'><input onClick={handleRadio} name="choosen-table" type="radio" value="code"  checked={isCode}/>Коды транзакций</p>
        </div>
        </label>
        <fieldset className='form-section__btns'>
            <label className="form-section__file">
                Выберите файл
                <input className="form-section__file-input" type="file" onChange={handleInput} ref={fileInput} accept=".csv"/>
            </label>
            <p className="form-section__file-name">{fileName}</p>
            <button type="submit" className='form-section__btn'>Загрузить</button>
        </fieldset>
        </form> 
    </div>
  )
}


export default FormSection;
