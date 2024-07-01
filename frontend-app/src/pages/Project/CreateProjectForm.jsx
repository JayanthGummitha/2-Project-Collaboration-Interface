import { createProject } from '@/Redux/Project/Action'
import { Button } from '@/components/ui/button'
import { DialogClose } from '@/components/ui/dialog'
import { Form, FormControl, FormField, FormItem, FormMessage } from '@/components/ui/form'
import { Input } from '@/components/ui/input'
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select'
import { Cross1Icon } from '@radix-ui/react-icons'
import React from 'react'
import { useForm } from 'react-hook-form'
import { useDispatch } from 'react-redux'
const tags=[
  'all',"java",'react','nextjs','spring boot','mysql','mongodb','angular','python','flask','django'
]

const CreateProjectForm = () => {
  const dispatch=useDispatch()
  const handleTagsChange=(newValue)=>{
    const currentTags=form.getValues("tags");
    const updatedTags=currentTags.includes(newValue)?
    currentTags.filter(tag=>tag!==newValue):[...currentTags,newValue]
    form.setValue("tags",updatedTags)
 }
    const form=useForm({
        // resolver
        defaultValues:{
            name:"",
            description:"",
            category:"",
            tags:["javascript","react"]
        }
    })

    const onSubmit=(data)=>{
      dispatch(createProject(data))
        // console.log("create new project", data)
    }
  return (
    <div>
         <Form {...form}>
              <form className="space-y-3"onSubmit={form.handleSubmit(onSubmit)}>
                <FormField
                name="name"
                render={ ({field})=>
                (
                  <FormItem>
                  <FormControl>
                    <Input {...field} type="text" className="border w-full border-gray-700 py-5 px-5" placeholder="project name"/>
                  </FormControl>
                  <FormMessage/>
                </FormItem>
                )}/>
                
                <FormField
                name="description"
                render={({field})=>(
                  <FormItem>
                    <FormControl>
                      <Input {...field} type="text" className="border w-full border-gray-700 py-5 px-5" placeholder="description"/>
                    </FormControl>
                  </FormItem>
                )}/>
                <FormField
                name="category"
                render={({field})=>(
                  <FormItem>
                    <FormControl>
                      <Select defaultValue='full stack' value={field.value}
                      onValueChange={(value)=>{
                        field.onChange(value)
                      }}>
                        <SelectTrigger>
                          <SelectValue placeholder="Category"/>
                        </SelectTrigger>
                        <SelectContent>
                          <SelectItem value="full stack">
                            full stack
                          </SelectItem>
                          <SelectItem value="frontend">
                            frontend
                          </SelectItem>
                          <SelectItem value="backend">
                            backend
                          </SelectItem>
                          
                        </SelectContent>
                      </Select>
                    </FormControl>
                  </FormItem>
                )}/>
                <FormField
                name="tags"
                render={({field})=>(
                  <FormItem>
                    <FormControl>
                      <Select defaultValue='spring boot' 
                      // value={field.value}
                      onValueChange={(value)=>{
                        handleTagsChange(value)
                      }}>
                        <SelectTrigger>
                          <SelectValue placeholder="tags"/>
                        </SelectTrigger>
                        <SelectContent>
                          {tags.map((item)=><SelectItem value={item}>
                            {item}
                          </SelectItem>
                          )}
                          
                        </SelectContent>
                      </Select>
                    </FormControl>
                    <div className='flex flex-wrap gap-1'>
                     {field.value.map((item)=>
                       <div  onClick={()=>handleTagsChange(item)}className='flex rounded-full item-center cursor-pointer gap-2 border px-4 py-1'>
                       <span className='text-sm'>{item}</span>
                       <Cross1Icon className='w-3 h-3 mt-1'/>
                     </div>
                     )}
                    </div>
                    <FormMessage/>
                  </FormItem>
                )}/>
                <DialogClose>
                  { false? <div>
                    <p>
                      you can only create 3 project with free plan, please upgrade your plan
                    </p>

                  </div>
                  :
                  <Button type="submit" className="w-full my-5">Create Project</Button>

                  }
                </DialogClose>
              </form>
         </Form>
    </div>
  )
}

export default CreateProjectForm