// import './style/App.scss'; // Import the main stylesheet
import Header from './component2/Header'; // Import the Header component
import Body from './component2/Body'; // Import the Body component
import { FileContextProvider } from './component2/FileContext.js'; // Import the FileContextProvider to manage file upload state

/**
 * Main App component that renders the application layout.
 * It includes the Header and Body components.
 * 
 * @returns {JSX.Element} The main application component with Header and Body.
 */
function App() {
  return (
    <>
      {/* Render the Header component */}
      <Header/>

      {/* Provide FileContext to the Body component */}
      <FileContextProvider>
        <Body/>
      </FileContextProvider>    
    </>
  );
}

export default App;
