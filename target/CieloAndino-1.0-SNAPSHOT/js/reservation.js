
document.addEventListener("DOMContentLoaded", async () => {
    const lista = await getReservas();
    createCards(lista);
    
    const btnInsertar = document.getElementById("btn-insert");
    btnInsertar.addEventListener("click", async () => {
        const res = await insertReserva();
        console.log(res);
        if(res.success){
            alert("insertado correctamente");
            const lista = await getReservas();
            createCards(lista);    

        }else{
            alert("No se pudo insertar la reserva");
        }
    });
    
    
});

async function getReservas(){
    try {
        const response = await fetch("ReservationServerlet");
        if(!response.ok){
            return {
                success: false
            };
        }
        const res = await response.json();
        
        return res;
        
    }catch (error){
        return {
            success: false
        };
    }
};
function updateReserva(){
    
};
async function deleteReserva(id){
    try{
        const response = await fetch(`ReservationServerlet?id=${id}`, {
            method: "DELETE"
        });
        if(!response.ok){
            return {
              success: false  
            };
        }   
        
        const res = await response.json();
        return res;
    }
    catch (error){
        return {
            success: false
        };
    }
};
const insertReserva = async () => {
    try{
        const datos = new URLSearchParams();
        datos.append("fecha_ingreso", document.getElementById("fecha-ingreso").value);
        datos.append("fecha_salida", document.getElementById("fecha-salida").value);
        

        const response = await fetch("ReservationServerlet", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },  
            body: datos
        });
        if(!response.ok){
            return {
              success: false  
            };
        }   
        
        const res = await response.json();
        console.log(res);
        return res;
    }
    catch (error){
        return {
            success: false
        };
    }
};

const createCards = (list) => {
    console.log(list);
    const padre = document.getElementById("lista-reservas");
    padre.innerHTML = "";
    if(list.length === 0){
        const alerta = document.createElement("div");
        
        alerta.className = "alert alert-info text-center mt-4";
        alerta.setAttribute("role", "alert");
        
        alerta.textContent = "No se encontraron reservas";
        padre.appendChild(alerta);
    }else {
        list.forEach((item) => {
           const card = createCard(item);
           padre.appendChild(card);
        });
    }
};



const createCard = (reserva) => {
    const card = document.createElement("div");
    card.className = "card border-dark mb-3";
    card.style.maxWidth = "100%";

    const cardBody = document.createElement("div");
    cardBody.className = "card-body";

    const ingresoP = document.createElement("p");
    ingresoP.className = "card-text";
    ingresoP.textContent = `Fecha ingreso: ${reserva.fechaInicio}`;

    const salidaP = document.createElement("p");
    salidaP.className = "card-text";
    salidaP.textContent = `Fecha salida: ${reserva.fechaFin}`;

    const btnEditar = document.createElement("button");
    btnEditar.className = "btn btn-primary me-2";
    btnEditar.textContent = "Editar";
    btnEditar.addEventListener("click", async () => {
        
    });

    const btnEliminar = document.createElement("button");
    btnEliminar.className = "btn btn-danger";
    btnEliminar.textContent = "Eliminar";
    btnEliminar.addEventListener("click", async () => {
        const res = await deleteReserva(reserva.id);
        if(res.success){
            alert("eliminado correctamente");
            const lista = await getReservas();
            createCards(lista); 
        }else{
            alert("Error eliminando");
        }
    });

    cardBody.appendChild(ingresoP);
    cardBody.appendChild(salidaP);
    cardBody.appendChild(btnEditar);
    cardBody.appendChild(btnEliminar);

    card.appendChild(cardBody);

    return card;
} 
