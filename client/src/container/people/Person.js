import React, { Component } from 'react';
import {
    Card, CardImg, CardBody,
    CardTitle
} from 'reactstrap';

class Person extends Component {

    render() {
        const data = this.props.data;

        return (

            <Card>
                <CardImg top  width="100%"  src={data.photoUrl} />
                <CardBody>
                    <CardTitle>{data.name}</CardTitle>
                </CardBody>
            </Card>

        )
    }
}

export default Person;