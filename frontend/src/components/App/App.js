import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import ShoppingCart from "../ShoppingCart/shoppingCart";
import Authors from '../Authors/authors';
import Books from '../Books/BookList/books';
import Header from '../Header/header';
import BookAdd from '../Books/BookAdd/bookAdd';
import EShopService from "../../repository/eshopRepository";
import BookEdit from "../Books/BookEdit/bookEdit";
import Select from 'react-select';
import Login from "../Login/login";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            authors: [],
            books: [],
            shoppingCart: [],
            selectedBook: {}

        }
    }

    render() {

        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">

                        <Route path={"/authors"} exact render={() =>
                            <Authors authors={this.state.authors}/>}/>
                        <Route path={"/shopping-cart"} exact render={() =>
                            <ShoppingCart shoppingCart={this.state.shoppingCart}/>}/>
                        <Route path={"/books"} exact render={() =>
                            <Books books={this.state.books}/>}/>
                        <Route path={"/books/add"} exact render={() =>
                            <BookAdd shoppingCart={this.state.shoppingCart}
                                        authors={this.state.authors}
                                        onAddBook={this.addBook}/>}/>
                        <Route path={"/books/edit/:id"} exact render={() =>
                            <BookEdit shoppingCart={this.state.shoppingCart}
                                         authors={this.state.author}
                                         onEditBook={this.editBook}
                                         book={this.state.selectedBook}/>}/>
                        <Route path={"/books"} exact render={() =>
                            <Books books={this.state.books}
                                      onDelete={this.deleteBook}
                                      onEdit={this.getBook}/>}
                               selectedYear={this.state.selectedYear}/>
                        {/*<Route path={"/login"} exact render={() => <Login onLogin={this.fetchData}/>}/>*/}
                        <Redirect to={"/books"}/>
                    </div>
                </main>
            </Router>
        );
    }


    loadAuthors = () => {
        EShopService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }

    loadBooks = () => {
        const year=this.state.selectedYear
        EShopService.fetchBooks(year)
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    loadShoppingCart = () => {
        EShopService.fetchShoppingCart()
            .then((data) => {
                this.setState({
                    shoppingCart: data.data
                })
            });
    }

    deleteBook = (id) => {
        EShopService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    addBook = (title, price, author,genre,publicationHouse,publicationYear,imageData) => {
        EShopService.addBook(title, price, author,genre,publicationHouse,publicationYear,imageData)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        EShopService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }

    editBook = (id,title, price, author,genre,publicationHouse,publicationYear,imageData) => {
        EShopService.editBook(id,title, price, author,genre,publicationHouse,publicationYear,imageData)
            .then(() => {
                this.loadBooks();
            });
    }
    componentDidMount() {
        this.fetchData()
    }

    fetchData = () => {
        this.loadAuthors();
        this.loadBooks();
        this.loadShoppingCart();
    }
}
export default App;
