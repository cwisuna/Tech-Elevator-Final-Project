<template>
  <Transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
            <slot></slot>
        </div>
      </div>
    </div>
  </Transition>
</template>

<style scope>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
}
.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}
.modal-container {
  display: flex;
  padding: 50px;
  flex-direction: column;
  text-align: center;
  align-items: center;
  gap: 2rem;
  width: 600px;
  margin: 0px auto;
  background-color: #fff;
  color: #004E64;
  font-family: 'Montserrat Alternates', 'Franklin Gothic Medium', 'Arial Narrow', 'Arial';
}
</style>