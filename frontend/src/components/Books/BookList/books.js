import React from "react";
import ReactPaginate from 'react-paginate'
import BookTerm from '../BookTerm/bookTerm';
import Select from 'react-select'
import {Link} from 'react-router-dom';
import eshopRepository from "../../../repository/eshopRepository";



class Books extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            page: 0,
            size: 2,
            selectedYear:null
        }
    }


    render() {
        const yearOptions = [];
        for (let year = 1960; year <= 2024; year++) {
            yearOptions.push({ value: year, label: year.toString() });
        }
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.books.length / this.state.size);
        const books = this.getBooksPage(offset, nextPageOffset);
        const selectedYear = this.props.selectedYear;
        const filteredBooks = selectedYear
            ? this.props.books.filter((book) => book.publicationYear === selectedYear)
            : this.props.books;
        const displayedBooks = this.getBooksPage(offset, nextPageOffset, filteredBooks);

        console.log(books, pageCount)

        return (

            <div className={"container mm-4 mt-5"}>
                <Select
                    value={this.state.selectedYear}
                    onChange={this.handleYearChange}
                    options={yearOptions}
                    placeholder="Select Year"
                />
                <div className={"row"}>
                    <div className={"table-responsive"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Title</th>
                                <th scope={"col"}>Price</th>
                                <th scope={"col"}>Genre</th>
                                <th scope={"col"}>Publication Year</th>
                                <th scope={"col"}>Publication House</th>
                                <th scope={"col"}>Image Data</th>
                                <th scope={"col"}>Author</th>
                            </tr>
                            </thead>
                            <tbody>
                            {displayedBooks}
                            </tbody>
                        </table>
                    </div>
                    <div className="col mb-3">
                        <div className="row">
                            <div className="col-sm-12 col-md-12">
                                <Link className={"btn btn-block btn-dark"} to={"/books/add"}>Add new book</Link>
                            </div>
                        </div>
                    </div>
                </div>
                <ReactPaginate previousLabel={"back"}
                               nextLabel={"next"}
                               breakLabel={<a href="/#">...</a>}
                               breakClassName={"break-me"}
                               pageClassName={"ml-1"}
                               pageCount={pageCount}
                               marginPagesDisplayed={2}
                               pageRangeDisplayed={5}
                               onPageChange={this.handlePageClick}
                               containerClassName={"pagination m-4 justify-content-center"}
                               activeClassName={"active"}/>
            </div>
        )
    }
    handleYearChange = (selectedOption) => {
        this.setState({ selectedYear: selectedOption.value });
    }
    handlePageClick = (data) => {
        let selected = data.selected;
        console.log(selected)
        this.setState({
            page: selected
        })
    }

    getBooksPage = (offset, nextPageOffset) => {
        console.log(offset, nextPageOffset)
        return this.props.books.map((term, index) => {
            return (
                <BookTerm term={term} onDelete={this.props.onDelete} onEdit={this.props.onEdit}/>
            );
        }).filter((book, index) => {
            return index >= offset && index < nextPageOffset;
        })
    }
}
export default Books;