import axios from '../custom-axios/axios';

const EShopService = {
    fetchAuthors: () => {
        return axios.get("/authors");
    },
    fetchShoppingCart: () => {
        return axios.get("/shopping-cart");
    },
    fetchBooks: () => {
        return axios.get("/books");
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    addBook: (title, price, author,genre,publicationHouse,publicationYear,imageData) => {
        return axios.post("/books/add", {
            "title": title,
            "price": price,
            "genre": genre,
            "publicationHouse": publicationHouse,
            "publicationYear": publicationYear,
            "imageData": imageData,
            "author": author
        });
    },
    editBook: (id,title, price, author,genre,publicationHouse,publicationYear,imageData) => {
        return axios.put(`/books/edit/${id}`, {
            "title": title,
            "price": price,
            "genre": genre,
            "publicationHouse": publicationHouse,
            "publicationYear": publicationYear,
            "imageData": imageData,
            "author": author
        });
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    login: (username, password) => {
        return axios.post("/login", {
            "username": username,
            "password": password
        });
    }
}

export default EShopService;
