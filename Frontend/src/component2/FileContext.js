import React, { useState, useContext, useEffect } from 'react';

// Create a context for managing file-related state
const FileContext = React.createContext();

/**
 * Custom hook to access the FileContext values.
 * 
 * @returns {Object} The context values from FileContext.
 */
export function useFileContext() {
    return useContext(FileContext);
}

/**
 * Provider component to wrap the application and provide the context values.
 * 
 * @param {Object} props - The component props.
 * @param {React.ReactNode} props.children - The child components that will consume the context.
 * @returns {JSX.Element} The FileContext.Provider with context values.
 */
export function FileContextProvider({ children }) {
    // State to manage file upload status
    const [fileUploaded, setFileUploaded] = useState(null);

    // State to manage the current chart type or data
    const [chart, setChart] = useState(null);

    // State to manage the image received after processing
    const [imageReceived, setImageReceived] = useState(null);

    // State to manage a boolean flag (purpose not specified)
    const [f, setF] = useState(false);

    // Effect to log fileUploaded state changes
    useEffect(() => {
        console.log(fileUploaded);        
    }, [fileUploaded]);

    // Effect to log chart state changes
    useEffect(() => {
        console.log(chart);        
    }, [chart]);

    // Context value object
    const value = {
        fileUploaded,
        setFileUploaded,
        chart,
        setChart,
        imageReceived,
        setImageReceived,
        f,
        setF
    };

    return (
        // Provide the context value to children components
        <FileContext.Provider value={value}>
            {children}
        </FileContext.Provider>
    );
}
