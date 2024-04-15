import React from "react";
import {useHistory} from "react-router-dom";

const BookEdit = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        title: "",
        price: 0,
        publicationHouse: 0,
        publicationYear: 0,
        author: 0,
        imageData: 0,
        genre: 0
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const title = formData.title !== "" ? formData.title : props.books.title;
        const price = formData.price !== 0 ? formData.price : props.books.price;
        const publicationHouse = formData.publicationHouse !== 0 ? formData.publicationHouse : props.books.publicationHouse;
        const publicationYear = formData.publicationYear !== 0 ? formData.publicationYear : props.books.publicationYear;
        const genre = formData.genre !== 0 ? formData.genre: props.books.genre;
        const imageData = formData.imageData !== 0 ? formData.imageData: props.books.imageData;
        const author = formData.author !== 0 ? formData.author : props.books.author.id;

        props.onEditBook(props.books.id, title, price, publicationYear, publicationHouse, author,genre,imageData);
        history.push("/books");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="Title">Book title</label>
                        <input type="text"
                               className="form-control"
                               id="title"
                               name="title"
                               placeholder={props.books.title}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">Price</label>
                        <input type="text"
                               className="form-control"
                               id="price"
                               name="price"
                               placeholder={props.books.price}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="publicationYear">Publication Year</label>
                        <input type="text"
                               className="form-control"
                               id="publicationHouse"
                               name="publicationHouse"
                               placeholder={props.books.publicationYear}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="publicationHouse">Publication House</label>
                        <input type="text"
                               className="form-control"
                               id="publicationHouse"
                               name="publicationHouse"
                               placeholder={props.books.publicationHouse}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="genre">Genre</label>
                        <input type="text"
                               className="form-control"
                               id="genre"
                               name="genre"
                               placeholder={props.books.genre}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="imageData">Image Data</label>
                        <input type="text"
                               className="form-control"
                               id="imageData"
                               name="imageData"
                               placeholder={props.books.imageData}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select name="author" className="form-control" onChange={handleChange}>
                            {props.authors.map((term) => {
                                if(props.books.authors !== undefined &&
                                    props.books.authors.id === term.id)
                                    return <option selected={props.books.authors.id} value={term.id}>{term.name}</option>
                                else return <option value={term.id}>{term.name}</option>
                            })}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}
export default BookEdit;