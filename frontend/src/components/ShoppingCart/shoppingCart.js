import React from 'react';

const shoppingCart = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>User Name</th>
                            <th scope={"col"}>Quantity</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.shoppingCart.map((term) => {
                            return (
                                <tr>
                                    <td>{term.username}</td>
                                    <td>{term.quantity}</td>
                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}

export default shoppingCart;
