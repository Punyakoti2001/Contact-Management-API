import React from 'react'
import { Link } from 'react-router-dom';

export default function Home() {
  return (
    <div className="container mt-5 text-center">
      <h1>Welcome to the Contact Management System</h1>
      <p>Use the links below to add a contact or view all contacts.</p>
      <div className="mt-4">
        <Link to="/contact/add" className="btn btn-success me-3">
          Add Contact
        </Link>
        <Link to="/all-contacts" className="btn btn-warning">
          Display All Contacts
        </Link>
      </div>
    </div>
  )
}
