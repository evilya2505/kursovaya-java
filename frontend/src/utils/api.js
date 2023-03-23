class Api {
  constructor(options) {
    this._baseUrl = options.baseUrl;
    this._headers = options.headers;
  }

  _getRequestResult(res) {
    if (res.ok) {
      return res.json();
    } else {
      return Promise.reject(`Ошибка: ${res.status}`);
    }
  }

  getAbsTransactionById(id) {
    return fetch(`${this._baseUrl}/abs-transaction/${id}`, {
      headers: {
        ...this._headers
      }
    })
      .then(this._getRequestResult);
  }

  getMaxTransactionById(id) {
    return fetch(`${this._baseUrl}/max-transaction/${id}`, {
      headers: {
        ...this._headers
      }
    })
      .then(this._getRequestResult);
  }

  postCsv(file, type) {
    const data = new FormData();
    // data.append('key', JSON.stringify('file'));
    data.append('file', file);

    return fetch(`${this._baseUrl}/${type}/upload-csv`, {
      method: 'POST',
      body: data
    })
      .then(this._getRequestResult);
  }

  getMaxGrouppedByInfo() {
    return fetch(`${this._baseUrl}/max-transactions`, {
        headers: {
          ...this._headers
        }
      })
        .then(this._getRequestResult);
  }

  getAbsGrouppedByInfo() {
    return fetch(`${this._baseUrl}/abs-transactions`, {
        headers: {
          ...this._headers
        }
      })
        .then(this._getRequestResult);
  }
}

// Создание экземпляра класса Api
const api = new Api({
  baseUrl: 'http://localhost:8080',
  headers: {
    'Content-Type': 'application/json'
  }
});

export default api;
