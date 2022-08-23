import React from 'react'

export default function Header() {
  return (
    <div>
      <header>
        <nav className = "navbar navbar-expand-md navbar-dark bg-dark">
          <div className='mx-auto' >
              <a className = "navbar-brand" style={{ fontSize:35, color:'white'}}> Employee Management Application </a>
          </div>
        </nav>
      </header>
    </div>
  )
}
