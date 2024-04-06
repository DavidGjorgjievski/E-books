import React from "react";
import {useHistory} from "react-router-dom";

const BookAdd = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        title: "",
        price: 0,
        publicationHouse: 0,
        publicationYear: 0,
        author: 1,
        imageData: 0,
        genre: 7,

    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.title]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.title;
        const price = formData.price;
        const genre = formData.genre;
        const publicationHouse = formData.publicationHouse;
        const publicationYear = formData.publicationYear;
        const author = formData.author;

        props.onAddBook(name, price, genre, publicationHouse,publicationYear,author);
        history.push("/books");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="title">Book title</label>
                        <input type="text"
                               className="form-control"
                               id="title"
                               name="title"
                               required
                               placeholder="Enter book title"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="price">Price</label>
                        <input type="text"
                               className="form-control"
                               id="price"
                               name="price"
                               placeholder="Price"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="genre">Genre</label>
                        <input type="text"
                               className="form-control"
                               id="genre"
                               name="genre"
                               placeholder="Genre"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="Publication House">Publication House</label>
                        <input type="text"
                               className="form-control"
                               id="publicationHouse"
                               name="publicationHouse"
                               required
                               placeholder="Publication House"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="Publication Year">Publication Year</label>
                        <input type="text"
                               className="form-control"
                               id="publicationYear"
                               name="publicationYear"
                               required
                               placeholder="Publication Year"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Manufacturer</label>
                        <select name="author" className="form-control" onChange={handleChange}>
                            {props.authors.map((term) =>
                                <option value={term.id}>{term.name}</option>
                            )}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default BookAdd;