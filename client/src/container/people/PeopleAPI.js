const callGet = (page = 1, limit = 25, name = '' ) => {
    const endpoint = `/api/people?page_size=${limit}&page=${page}&name=${name}`

    return fetch(endpoint).then(response => response.json());
}

export default callGet;