import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table, Spinner } from 'reactstrap';
import AppNavbar from "../AppNavbar";
import { Link } from 'react-router-dom';

class Groups extends Component {
    constructor(props) {
        super(props);
        this.state = {groups: [], isLoading: true};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('/groups')
            .then(response => response.json())
            .then(data => this.setState({
                groups: data,
                isLoading: false
            }));
    }

    async remove(id) {
        await fetch(`/groups/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedEths = [...this.state.groups].filter(i => i.id !== id);
            this.setState({groups: updatedEths});
        });
    }

    render() {
        const {groups, isLoading} = this.state;

        if(isLoading) {
            return <Spinner color="primary" />;
        }

        const grps = groups.map(group => {
            return <tr key={group.id}>
                <td style={{whiteSpace: 'nowrap'}}>{group.name}</td>
                <td>{group.description}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/groups/" + group.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(group.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return(
            <div>
                <AppNavbar/>
                <br/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/groups/new">New</Button>
                    </div>
                    <h3>Groups</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Name</th>
                            <th width="70%">Description</th>
                            <th width="10%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {grps}
                        </tbody>
                    </Table>
                </Container>
            </div>
        )
    }
}

export default Groups;