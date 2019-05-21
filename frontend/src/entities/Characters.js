import React, {Component} from "react";
import {Spinner, Table, Container, ButtonGroup, Button, Modal, ModalBody, ModalHeader, ModalFooter, Badge} from 'reactstrap';
import AppNavbar from "../AppNavbar";
import {Link} from "react-router-dom";
import Markdown from "react-markdown";

class Characters extends Component {
    constructor(props) {
        super(props);
        this.state = {
            chars: [], isLoading: true, modal: false, selected: 0
        };
        this.remove = this.remove.bind(this);
        this.toggle = this.toggle.bind(this);
    }

    componentDidMount() {
        this.setState({isLoading: true, modal: false, selected: 0});

        fetch('/characters')
            .then(response => response.json())
            .then(data => this.setState({
                chars: data,
                isLoading: false
            }));
    }

    async remove(id) {
        await fetch(`/characters/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedChars = [...this.state.chars].filter(i => i.id !== id);
            this.setState({chars: updatedChars});
        });
    }

    toggle(index) {
        this.setState(prevState => ({
            modal: !prevState.modal,
            selected: index
        }));
    }

    render() {
        const {chars, isLoading, modal, selected} = this.state;
        let selectedChar = chars[selected];

        if(isLoading) {
            return <Spinner color="primary"/>
        }

        const characters = chars.map((char, index) => {
            return <tr key={char.id}>
                <td style={{whiteSpace: 'nowrap'}}>{char.name}</td>
                <td>{char.group.name}</td>
                <td>{char.skin}</td>
                <td>{char.hair}</td>
                <td>{char.eyes}</td>
                <td>{char.size}</td>
                <td>{char.age}</td>
                <td>{char.region.name}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="info" onClick={() => this.toggle(index)}>View</Button>
                        <Button size="sm" color="success" tag={Link} to={"/char/" + char.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(char.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return(
            <div>
                <AppNavbar/>
                <br/>
                <Container>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/characters/new">New</Button>
                    </div>
                    <h3>Characters</h3>
                    <br/>
                    <Modal size="lg" isOpen={modal} toggle={() => this.toggle(selected)} className={this.props.className}>
                        <ModalHeader toggle={() => this.toggle(selected)}>{selectedChar.name + ' ( '}<i>{selectedChar.group.name}</i>{' | '}<i>{selectedChar.region.name}</i>{' )'}</ModalHeader>
                        <ModalBody>
                            <h4>Appearance</h4>
                            <Table>
                                <tbody>
                                <tr key="size">
                                    <td>Size:</td>
                                    <td>{selectedChar.size}</td>
                                </tr>
                                <tr key="age">
                                    <td>Age:</td>
                                    <td>{selectedChar.age}</td>
                                </tr>
                                <tr key="skin">
                                    <td>Skin Tone:</td>
                                    <td>{selectedChar.skin}</td>
                                </tr>
                                <tr key="eye">
                                    <td>Eye Color:</td>
                                    <td>{selectedChar.eyes}</td>
                                </tr>
                                <tr key="hair">
                                    <td>Hair Color:</td>
                                    <td>{selectedChar.hair}</td>
                                </tr>
                                <tr key="size">
                                    <td>Features:</td>
                                    <td>
                                        {selectedChar.features.map(feature =>
                                            <Badge color="dark" style={{marginRight: 5}}>
                                                {feature}
                                            </Badge>
                                        )}
                                    </td>
                                </tr>
                                </tbody>
                            </Table>
                            <h4>Description</h4>
                            <Markdown source={selectedChar.description}/>
                        </ModalBody>
                        <ModalFooter>
                            <Button size="sm" color="success" tag={Link} to={"/char/" + selectedChar.id}>Edit</Button>
                            <Button size="sm" color="danger" onClick={() => this.remove(selectedChar.id)}>Delete</Button>
                        </ModalFooter>
                    </Modal>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Name</th>
                            <th width="10%">Group</th>
                            <th width="10%">Skin</th>
                            <th width="10%">Hair</th>
                            <th width="10%">Eye</th>
                            <th width="5%">Size</th>
                            <th width="5%">Age</th>
                            <th width="10%">Region</th>
                            <th width="20%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {characters}
                        </tbody>
                    </Table>
                </Container>
            </div>);
    }
}

export default Characters;