import React from 'react';





const Button = ({ label, handleClick }) => (
  <button
    className="button"
    onClick={handleClick}
  >
    { label}
  </button>
);

export default Button;