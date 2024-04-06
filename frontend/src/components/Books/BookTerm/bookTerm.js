import React from "react";
import {Link} from 'react-router-dom';

const bookTerm = (props) => {
    return (
        <tr>
            <td>{props.term.title}</td>
            <td>{props.term.price}</td>
            <td>{props.term.genre}</td>
            <td>{props.term.publicationHouse}</td>
            <td>{props.term.publicationYear}</td>
            <td>{props.term.imageData}</td>
            <td>{props.term.author.name}</td>
            <td className={"text-right"}>
                <a title={"Delete"} className={"btn btn-danger"}
                   onClick={() => props.onDelete(props.term.id)}>
                    Delete
                </a>
                <Link className={"btn btn-info ml-2"}
                      onClick={() => props.onEdit(props.term.id)}
                      to={`/books/edit/${props.term.id}`}>
                    Edit
                </Link>
            </td>
        </tr>
    )
}
export default bookTerm;