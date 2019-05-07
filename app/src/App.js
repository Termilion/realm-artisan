import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  state = {
    isLoading: true,
    eths: []
  };

  async componentDidMount() {
    const eth_response = await fetch('/api/v1/eth');
    const char_response = await fetch('/api/v1/characters');
    const region_response = await fetch('/api/v1/regions');
    const eth_body = await eth_response.json();
    const char_body = await char_response.json();
    const region_body = await region_response.json();
    this.setState({eths: eth_body, chars: char_body, regions: region_body, isLoading: false});
  }

  render() {
    const {eths, chars, regions, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo"/>
            <h2>Regions</h2>
            {regions.map(reg =>
              <div key={reg.id}>
                {reg.name}
              </div>
            )}
            <h2>Ethnicities</h2>
            {eths.map(
                eth =>
                  <div key={eth.id}>
                    {eth.name}
                  </div>
                )}
            <h2>Characters</h2>
            {chars.map(char =>
              <div key={char.id}>
                {char.name}
              </div>
            )}
          </header>
        </div>
    );
  }
}

export default App;
